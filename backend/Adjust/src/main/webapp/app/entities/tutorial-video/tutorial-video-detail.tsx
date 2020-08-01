import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tutorial-video.reducer';
import { ITutorialVideo } from 'app/shared/model/tutorial-video.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITutorialVideoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TutorialVideoDetail = (props: ITutorialVideoDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { tutorialVideoEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.tutorialVideo.detail.title">TutorialVideo</Translate> [<b>{tutorialVideoEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="adjustTutorialVideoId">
              <Translate contentKey="adjustApp.tutorialVideo.adjustTutorialVideoId">Adjust Tutorial Video Id</Translate>
            </span>
          </dt>
          <dd>{tutorialVideoEntity.adjustTutorialVideoId}</dd>
          <dt>
            <span id="content">
              <Translate contentKey="adjustApp.tutorialVideo.content">Content</Translate>
            </span>
          </dt>
          <dd>
            {tutorialVideoEntity.content ? (
              <div>
                {tutorialVideoEntity.contentContentType ? (
                  <a onClick={openFile(tutorialVideoEntity.contentContentType, tutorialVideoEntity.content)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {tutorialVideoEntity.contentContentType}, {byteSize(tutorialVideoEntity.content)}
                </span>
              </div>
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/tutorial-video" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/tutorial-video/${tutorialVideoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ tutorialVideo }: IRootState) => ({
  tutorialVideoEntity: tutorialVideo.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TutorialVideoDetail);
