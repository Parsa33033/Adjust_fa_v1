import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITutorial } from 'app/shared/model/tutorial.model';
import { getEntities as getTutorials } from 'app/entities/tutorial/tutorial.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './tutorial-video.reducer';
import { ITutorialVideo } from 'app/shared/model/tutorial-video.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITutorialVideoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TutorialVideoUpdate = (props: ITutorialVideoUpdateProps) => {
  const [tutorialId, setTutorialId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { tutorialVideoEntity, tutorials, loading, updating } = props;

  const { content, contentContentType } = tutorialVideoEntity;

  const handleClose = () => {
    props.history.push('/tutorial-video');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getTutorials();
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
        ...tutorialVideoEntity,
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
          <h2 id="adjustApp.tutorialVideo.home.createOrEditLabel">
            <Translate contentKey="adjustApp.tutorialVideo.home.createOrEditLabel">Create or edit a TutorialVideo</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : tutorialVideoEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="tutorial-video-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="tutorial-video-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="adjustTutorialVideoIdLabel" for="tutorial-video-adjustTutorialVideoId">
                  <Translate contentKey="adjustApp.tutorialVideo.adjustTutorialVideoId">Adjust Tutorial Video Id</Translate>
                </Label>
                <AvField id="tutorial-video-adjustTutorialVideoId" type="string" className="form-control" name="adjustTutorialVideoId" />
              </AvGroup>
              <AvGroup>
                <AvGroup>
                  <Label id="contentLabel" for="content">
                    <Translate contentKey="adjustApp.tutorialVideo.content">Content</Translate>
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
              <Button tag={Link} id="cancel-save" to="/tutorial-video" replace color="info">
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
  tutorials: storeState.tutorial.entities,
  tutorialVideoEntity: storeState.tutorialVideo.entity,
  loading: storeState.tutorialVideo.loading,
  updating: storeState.tutorialVideo.updating,
  updateSuccess: storeState.tutorialVideo.updateSuccess,
});

const mapDispatchToProps = {
  getTutorials,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TutorialVideoUpdate);
