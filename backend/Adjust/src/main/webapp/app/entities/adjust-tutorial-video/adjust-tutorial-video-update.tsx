import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAdjustTutorial } from 'app/shared/model/adjust-tutorial.model';
import { getEntities as getAdjustTutorials } from 'app/entities/adjust-tutorial/adjust-tutorial.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './adjust-tutorial-video.reducer';
import { IAdjustTutorialVideo } from 'app/shared/model/adjust-tutorial-video.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAdjustTutorialVideoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdjustTutorialVideoUpdate = (props: IAdjustTutorialVideoUpdateProps) => {
  const [tutorialId, setTutorialId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { adjustTutorialVideoEntity, adjustTutorials, loading, updating } = props;

  const { content, contentContentType } = adjustTutorialVideoEntity;

  const handleClose = () => {
    props.history.push('/adjust-tutorial-video');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getAdjustTutorials();
  }, []);

  const onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => props.setBlob(name, data, contentType), isAnImage);
  };

  const clearBlob = name => () => {
    props.setBlob(name, undefined, undefined);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...adjustTutorialVideoEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="adjustApp.adjustTutorialVideo.home.createOrEditLabel">
            <Translate contentKey="adjustApp.adjustTutorialVideo.home.createOrEditLabel">Create or edit a AdjustTutorialVideo</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : adjustTutorialVideoEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="adjust-tutorial-video-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="adjust-tutorial-video-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <AvGroup>
                  <Label id="contentLabel" for="content">
                    <Translate contentKey="adjustApp.adjustTutorialVideo.content">Content</Translate>
                  </Label>
                  <br />
                  {content ? (
                    <div>
                      {contentContentType ? (
                        <a onClick={openFile(contentContentType, content)}>
                          <Translate contentKey="entity.action.open">Open</Translate>
                        </a>
                      ) : null}
                      <br />
                      <Row>
                        <Col md="11">
                          <span>
                            {contentContentType}, {byteSize(content)}
                          </span>
                        </Col>
                        <Col md="1">
                          <Button color="danger" onClick={clearBlob('content')}>
                            <FontAwesomeIcon icon="times-circle" />
                          </Button>
                        </Col>
                      </Row>
                    </div>
                  ) : null}
                  <input id="file_content" type="file" onChange={onBlobChange(false, 'content')} />
                  <AvInput type="hidden" name="content" value={content} />
                </AvGroup>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/adjust-tutorial-video" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  adjustTutorials: storeState.adjustTutorial.entities,
  adjustTutorialVideoEntity: storeState.adjustTutorialVideo.entity,
  loading: storeState.adjustTutorialVideo.loading,
  updating: storeState.adjustTutorialVideo.updating,
  updateSuccess: storeState.adjustTutorialVideo.updateSuccess,
});

const mapDispatchToProps = {
  getAdjustTutorials,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustTutorialVideoUpdate);
