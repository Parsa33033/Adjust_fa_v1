import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './adjust-tutorial-video.reducer';
import { IAdjustTutorialVideo } from 'app/shared/model/adjust-tutorial-video.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdjustTutorialVideoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdjustTutorialVideoDetail = (props: IAdjustTutorialVideoDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { adjustTutorialVideoEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.adjustTutorialVideo.detail.title">AdjustTutorialVideo</Translate> [
          <b>{adjustTutorialVideoEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="content">
              <Translate contentKey="adjustApp.adjustTutorialVideo.content">Content</Translate>
            </span>
          </dt>
          <dd>
            {adjustTutorialVideoEntity.content ? (
              <div>
                {adjustTutorialVideoEntity.contentContentType ? (
                  <a onClick={openFile(adjustTutorialVideoEntity.contentContentType, adjustTutorialVideoEntity.content)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {adjustTutorialVideoEntity.contentContentType}, {byteSize(adjustTutorialVideoEntity.content)}
                </span>
              </div>
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/adjust-tutorial-video" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/adjust-tutorial-video/${adjustTutorialVideoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ adjustTutorialVideo }: IRootState) => ({
  adjustTutorialVideoEntity: adjustTutorialVideo.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustTutorialVideoDetail);
